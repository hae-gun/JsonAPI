import pandas as pd
import json
with open('./TypeAndName.json','r', encoding="utf-8") as json_file:
    json_data = json.load(json_file)
print(json.dumps(json_data,indent='\t'))