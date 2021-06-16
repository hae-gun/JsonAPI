import pandas as pd
import json
with open('./TypeAndName.json','r', encoding="utf-8") as json_file:
    json_data = json.load(json_file)

df = pd.DataFrame(json_data)


result = list()
for i, row in df.iterrows() :
    data = {'name':df.at[i,'Kor'],'num':df.at[i,'TypeNum'],'eng':df.at[i,'Eng'],'count':df.at[i,'PageCount']}
    result.append(data)
    
di={'data':result}
tmp = list()
tmp.append(di)
df = pd.DataFrame(tmp)
fileName='rearrange.json'
with open(fileName, 'w',encoding='utf-8') as file:
    df.to_json(file, force_ascii=False)
