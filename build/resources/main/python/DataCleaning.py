import pandas as pd
import json
with open('./TypeAndName.json','r', encoding="utf-8") as json_file:
    json_data = json.load(json_file)
#print(json.dumps(json_data,indent='\t',ensure_ascii=False))
#tmp = json.dumps(json_data,ensure_ascii=False)

#print(json_data)
df = pd.DataFrame(json_data)
#print(df)
#for data in df:
#    print(data)
    
수 = list()
트리 = list()
for i in range(len(df)):
    data=dict()
    data['num']=df['TypeNum'][i]
    data['name']=df['Kor'][i]
    if str(df['Kor'][i]).find('트리') != -1:
        트리.append(data)
    elif str(df['Kor'][i]).find('수') != -1:
        수.append(data)
    #print(df['TypeNum'][i],df['Kor'][i])

print(트리)
print(수)
