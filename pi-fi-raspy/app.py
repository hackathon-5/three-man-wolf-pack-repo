# app.py - Main application file. App is init and run from here.

import requests
import subprocess
import urllib



# class for the app so we can holder some instance data, methods
class PifiApp:

  # constructor
  def __init__(self):
    self.accessToken = 'bleh'
    self.api = 'http://localhost:1337/api'
    self.proximity =-60


  def determineSong(self, addrRssiTuple):

    visitedAddresses = []
    sortedTuples = sorted(addrRssiTuple, key=lambda addrRssi: addrRssi[1]) 
    for nearestDevice in sortedTuples:
      if nearestDevice[1] > self.proximity and nearestDevice[0] not in visitedAddresses:
        print(nearestDevice[0] + ' attempting device.')    
        try:
          self.getUserByBluetooth(nearestDevice[0])
        except:
          print('Unable to find device for %s' % nearestDevice[0])


  def login(self):
    payload = {'grant_type': 'password', 'username': 'kckolz@gmail.com', 'password': 'password'}
    request = requests.post(self.api + '/login', payload, auth=('kckolz', 'password'))
    self.accessToken = request.json()['access_token']

    headers = {'Authorization': 'Bearer '+self.accessToken}

    request = requests.get(self.api + '/api/spotify/login', headers=headers)

  def getUserByBluetooth(self, bluetooth):

    headers = {'Authorization': 'Bearer '+self.accessToken}
    request = requests.get(self.api + '/user/bluetooth/'+bluetooth, headers=headers)


  def getTrack(self, track):

    headers = {'Authorization': 'Bearer '+self.accessToken}
    request = requests.get(self.api + '/spotify/getTrack/'+track, headers=headers)
    return request.json()['preview_url']

  def playTrack(self, trackUrl):  

    urllib.urlretrieve(trackUrl, "song.mp3")
    player = subprocess.Popen(["mplayer",'song.mp3'], stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE)

    # player.stdin.write("q")

	
