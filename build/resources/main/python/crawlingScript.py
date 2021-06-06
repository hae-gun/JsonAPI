import requests
from bs4 import BeautifulSoup
import pandas as pd
import json
import time

def crawling(url,tier):
    params={'tier':tier}
    response = requests.get(url,params=params,verify=False)
    print(response.status_code)
    return response.text

def crawling2(url,tier,page):
    params={'tier':tier,'sort':'solvedac_asc','page':page}
    response = requests.get(url,params=params,verify=False)
    print(response.status_code)
    return response.text
tierName = dict()
tierName[1]='bronze'
tierName[2]='silver'
tierName[3]='gold'
tierName[4]='platinum'
tierName[5]='diamond'
tierName[6]='ruby'
    
#url=str(input())
probList = list()
for tier in range(1,31):
    prob=dict()
    url = 'https://www.acmicpc.net/problemset'
    html=crawling(url,tier)
    soup=BeautifulSoup(html,'html.parser')
    
    lnum=str(soup.select('body > div.wrapper > div.container.content > div:nth-child(6) > div:nth-child(2) > div > ul > li:last-child > a'))
    lastNum = lnum[lnum.index('>')+1:lnum.rindex('<')]
    print(lastNum)
    result = list()
    for page in range(1,int(lastNum)+1):
        #turl = url + '/' + str(page)
        #print(turl)
        html2=crawling2(url,tier,page)
        soup2=BeautifulSoup(html,'html.parser')
        nameNurl=soup2.select('#problemset > tbody > tr > td:nth-child(2)')
        for s in nameNurl:
            cont = dict()
            tmp = str(s).replace('<td>','').replace('</td>','')
            link = tmp[tmp.index('=')+1:tmp.index('>')]
            name = tmp[tmp.index('>')+1:tmp.rindex('<')]
            cont['id']=link[link.rindex('/')+1:-1]
            cont['name']=name
            cont['url']=link[1:-1]
            #print(cont)
            result.append(cont)
        #contents = str(nameNurl).split('>')
        #if page == 3:break
        time.sleep(1)
    prob['tier']=tier
    prob['list']=result
    probList.append(prob)
    if tier%5==0:
        df = pd.DataFrame(probList)
        fileName='tier_'+tierName[tier//5]+'.json'
        with open(fileName, 'w', encoding='utf-8') as file:
            df.to_json(file, force_ascii=False)
        probList=list()
    time.sleep(1)

#print(probList)

