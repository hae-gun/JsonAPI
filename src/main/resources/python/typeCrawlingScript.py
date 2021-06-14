#
import requests
from bs4 import BeautifulSoup
import pandas as pd
import json
import time

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

#print(result)
dictList = list()

#sort=ac_desc&algo=124
for data in result:
    typeDict = dict()
    tmp = str(data)
    url = tmp[tmp.find('"')+1:tmp.find('">')]
    name = tmp[tmp.find(">")+1:tmp.rfind("<")]
    typeDict['url'] = int(url[url.rfind('/')+1:])
    typeDict['name'] = name
    dictList.append(typeDict)

print(dictList)
#body > div.wrapper > div.container.content > div:nth-child(6) > div:nth-child(2) > div > ul > li:nth-last-child > a

probUrl = 'https://www.acmicpc.net/problemset'
#print(dictList[0]['url'])
html2 = crawling(probUrl,dictList[0]['url'])
soup2 = BeautifulSoup(html2,'html.parser')
print(soup2)
result2 = str(soup.select('body > div.wrapper > div.container.content > div:nth-child(6) > div:nth-child(2) > div > ul > li:last-child > a'))
print(result2)
#for i in range(0,2):
    

