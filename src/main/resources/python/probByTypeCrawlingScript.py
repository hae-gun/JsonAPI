import pandas as pd
import json
import requests
from bs4 import BeautifulSoup


with open('./TypeAndName.json','r', encoding="utf-8") as json_file:
    json_data = json.load(json_file)

df = pd.DataFrame(json_data)


result = list()

def crawling(url,algo,page):
    params={'sort':'as_desc','algo':algo,'algo_if':'and','page':page}
    response = requests.get(url,params=params,verify=False)
    print(response.status_code)
    return response.text
url = 'https://www.acmicpc.net/problemset'    
for i, row in df.iterrows() :
    page = int(df.at[i,'PageCount'])
    algo = int(df.at[i,'TypeNum'])
    print(page,algo)
    for i in range(1,page+1):
        html = crawling(url,algo,i)
        c = BeautifulSoup(html,'html.parser')
        #print(c)
        r = list(c.select('#problemset > tbody > tr > td:nth-child(1)'))
        print(len(r))
        break
    break

# #problemset > tbody > tr:nth-child(100) > td.list_problem_id    
    

# https://www.acmicpc.net/problemset?sort=ac_desc&algo=124&algo_if=and&page=1



#print(result)
