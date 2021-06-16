import requests
from bs4 import BeautifulSoup
import pandas as pd
import json
import time
import pickle

url = 'https://www.acmicpc.net/problem/tags'

def crawling(url):
    params={}
    response = requests.get(url,params=params,verify=False)
    print(response.status_code)
    return response.text
def crawling(url,algo):
    params={'sort':'as_desc','algo':algo}
    response = requests.get(url,params=params,verify=False)
    print(response.status_code)
    return response.text
param = dict()
html = crawling(url,param)

soup = BeautifulSoup(html,'html.parser')

result = list(soup.select('body > div.wrapper > div.container.content > div:nth-child(5) > div > div > table > tbody > tr > td:nth-child(1) > a'))
result2 = list(soup.select('body > div.wrapper > div.container.content > div:nth-child(5) > div > div > table > tbody > tr > td:nth-child(2) > a'))
print(result)
dictList = list()

for i in range(len(result)):
    data = result[i]
    eng = result2[i]
    typeDict = dict()
    tmp = str(data)
    url = tmp[tmp.find('"')+1:tmp.find('">')]
    name = tmp[tmp.find(">")+1:tmp.rfind("<")]
    typeDict['url'] = int(url[url.rfind('/')+1:])
    typeDict['name'] = name
    tmp2 = str(eng)
    typeDict['eng'] = tmp2[tmp2.find(">")+1:tmp2.rfind("<")].upper()
    dictList.append(typeDict)

print(dictList)
t = 0
tmp = list()
for data in dictList:
    probUrl = 'https://www.acmicpc.net/problemset'
    html2 = crawling(probUrl, data['url'])
    soup2 = BeautifulSoup(html2,'html.parser')
    #page 번호 추출
    r2 = str(soup2.select('body > div.wrapper > div.container.content > div:nth-child(6) > div:nth-child(2) > div > ul > li:last-child > a'))
    lastPage = int(r2[r2.find('>')+1:r2.rfind('<')])
    data['lastPage'] = lastPage
    tmp.append(data.values())
    # t += 1
    time.sleep(1)
    # if t==3:
    #     break


df = pd.DataFrame(tmp,columns=['TypeNum','Kor','Eng','PageCount'])
fileName='TypeAndName.json'
with open(fileName, 'w',encoding='utf-8') as file:
    df.to_json(file, force_ascii=False)

