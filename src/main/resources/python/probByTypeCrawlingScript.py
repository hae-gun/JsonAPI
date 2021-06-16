import pandas as pd
import json
import requests
from bs4 import BeautifulSoup
import time
import warnings
 
warnings.filterwarnings("ignore")

with open('./TypeAndName.json','r', encoding="utf-8") as json_file:
    json_data = json.load(json_file)

df = pd.DataFrame(json_data)


result = list()

def crawling(url,algo,page):
    params={'sort':'as_desc','algo':algo,'algo_if':'and','page':page}
    response = requests.get(url,params=params,verify=False)
    print(response.status_code)
    return response.text

def selectTypeNum(tag):
    num = str(tag)
    num = num[num.find('>')+1:num.rfind('<')]
    return num

result = list()
url = 'https://www.acmicpc.net/problemset'
nameIndex = 0
for i, row in df.iterrows() :
    dataType = dict()
    page = int(df.at[i,'PageCount'])
    algo = int(df.at[i,'TypeNum'])
    #print(page,algo)
    for j in range(1,page+1):
        html = crawling(url,algo,j)
        time.sleep(0.1)
        c = BeautifulSoup(html,'html.parser')
        #print(c)
        r = list(c.select('#problemset > tbody > tr > td:nth-child(1)'))
        try:
            dataType['list'] += r
        except:
            dataType['list']=list()
            dataType['list'] += r
    dataType['type'] = df.at[i,'Kor']
    dataType['typeNum']=algo
    print(dataType,'데이터 크롤링 완료')
    for idx in range(len(dataType['list'])):
        dataType['list'][idx] = selectTypeNum(dataType['list'][idx]) #문제번호추출
    result.append(dataType)
    # type 5개 단위로 잘라서 저장.
    if (int(i)+1)%5==0:
        print('저장할데이터')
        for tmpData in result:
            print(tmpData['type'])
        tmp = [result]
        df2 = pd.DataFrame(tmp)
        fileName='type_prob_'+str(nameIndex)+'.json'
        with open(fileName, 'w', encoding='utf-8') as file:
            df2.to_json(file, force_ascii=False)
        print('save data',fileName)
        # 리스트 초기화
        result=list()
        nameIndex += 1
    time.sleep(0.1)
    
        

    
# type : 알고리즘 유형
# list : 문제번호 리스트 -> 추후 BojVo Id와 매핑될 필드    
print(dataType)


