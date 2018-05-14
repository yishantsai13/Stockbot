import requests
from io import StringIO
import pandas as pd
import numpy as np
import requests
import argparse
from datetime import datetime, timedelta
from firebase import firebase
import twstock
import time
import datetime
import json

url='https://happysorry-2c3e7.firebaseio.com/'

fb=firebase.FirebaseApplication(url)





datestr = datetime.date.today().today().strftime("%Y%m%d")

datestr="20180402"
r = requests.post('http://www.twse.com.tw/exchangeReport/MI_INDEX?response=csv&date=' + datestr + '&type=ALL')
df = pd.read_csv(StringIO("\n".join([i.translate({ord(c): None for c in ' '}) 
for i in r.text.split('\n') 
    if len(i.split('",')) == 17 and i[0] != '='])), header=0)

for i in range(0,936):
    lst=df[i:i+1]
    try:
        num=lst['證券代號'].astype(int).values[0]/100
        for j in range(11,29):
            if num>j and num<j+1:
                a=lst['證券代號'].astype(str).values[0]
                b=lst["證券名稱"].astype(str).values[0]
                c=lst["成交股數"].astype(str).values[0]
                d=lst["成交筆數"].astype(str).values[0]
                e=lst["成交金額"].astype(str).values[0]
                f=lst["開盤價"].astype(str).values[0]
                g=lst["最高價"].astype(str).values[0]
                h=lst["最低價"].astype(str).values[0]
                ii=lst["收盤價"].astype(str).values[0]
                jj=lst["漲跌(+/-)"].astype(str).values[0]
                k=lst["漲跌價差"].astype(str).values[0]
                l=lst["最後揭示買價"].astype(str).values[0]
                m=lst["最後揭示買量"].astype(str).values[0]
                n=lst["最後揭示賣價"].astype(str).values[0]
                o=lst["最後揭示賣量"].astype(str).values[0]
                p=lst["本益比"].astype(str).values[0]
                
                
                '''file=[['證券代號','a'],['證券名稱', 'b'],['成交股數', 'c'],["成交筆數",'d'],["成交金額",'e'],["開盤價",'f'],["最高價",'g'],["最低價",'h'],["收盤價",'ii'],["漲跌(+/-)",'jj'],["漲跌價差",'k'],["最後揭示買價",'l'],["最後揭示買量",'m'],["最後揭示賣價",'n'],["最後揭示賣量",'o'],["本益比",'p']]
                print(file[0][1])'''
                data = {'證券代號': a, '證券名稱': b, '成交股數': c,'成交筆數':d,'成交金額':e,'開盤價':f,'最高價':g,'最低價':h,'收盤價':ii,'漲跌':jj,'漲跌價差':k,'最後揭示買價':l,'最後揭示買量':m,'最後揭示賣價':n,'最後揭示賣量':o,'本益比':p}
                string="/{0}/{1}".format(str(j),str(a))
                print(data)
                '''print(string)'''
                fb.post(string,data)

                
                
    except:
        '''print('exp',lst["證券代號"])'''
        