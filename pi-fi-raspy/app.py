# app.py - Main application file. App is init and run from here.

import requests

# class for the app so we can holder some instance data, methods
class PifiApp:
		

  # constructor
  def __init__(self):
    self.accessToken = 'bleh'
    self.api = 'http://localhost:1337/api'
    

  def login(self):
    payload = {'grant_type': 'password', 'username': 'kckolz@gmail.com', 'password': 'password'}
    request = requests.post(self.api + '/login', payload, auth=('kckolz', 'password'))
    self.accessToken = request.json()['access_token']

    headers = {'Authorization': 'Bearer '+self.accessToken}
    request = requests.get(self.api + '/api/spotify/login', headers=headers)



  def getTrack(self):
    print(self.accessToken)
    headers = {'Authorization': 'Bearer '+self.accessToken}
    request = requests.get(self.api + '/spotify/getTrack/2daZovie6pc2ZK7StayD1K', headers=headers)
    print(request.json())



	