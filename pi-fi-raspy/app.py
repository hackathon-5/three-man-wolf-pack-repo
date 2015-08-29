# app.py - Main application file. App is init and run from here.

import requests
import subprocess
import urllib



# class for the app so we can holder some instance data, methods
class PifiApp:

  # constructor
  def __init__(self):
    self.accessToken = 'bleh'
    self.api = 'http://52.20.116.83:1337/api'
    self.proximity =-53
    self.playing = False


  def determineSong(self, addrRssiTuple):

    visitedAddresses = []
    sortedTuples = sorted(addrRssiTuple, key=lambda addrRssi: addrRssi[1]) 
    for nearestDevice in reversed(sortedTuples):
      if nearestDevice[1] > self.proximity and nearestDevice[0] not in visitedAddresses:
        visitedAddresses.append(nearestDevice[0])
        print(nearestDevice[0] + ' attempting device.')    
        try:
          user = self.getUserByBluetooth(nearestDevice[0])
          print(nearestDevice[0])
          print(user)
          track = self.getTrack(user['tracks'][0])
          print(track)
          self.playTrack(track)

        except:
          print('Unable to find device for %s' % nearestDevice[0])


  def login(self):
    payload = {'grant_type': 'password', 'username': 'kckolz@gmail.com', 'password': 'password'}
    request = requests.post(self.api + '/login', payload, auth=('kckolz', 'password'))
    self.accessToken = request.json()['access_token']
    print('access token: %s' % self.accessToken)

    headers = {'Authorization': 'Bearer '+self.accessToken}

    request = requests.get(self.api + '/spotify/login', headers=headers)

  def getUserByBluetooth(self, bluetooth):

    headers = {'Authorization': 'Bearer '+self.accessToken}
    request = requests.get(self.api + '/user/bluetooth/'+urllib.quote(bluetooth), headers=headers)
    return request.json();

  def getTrack(self, track):

    headers = {'Authorization': 'Bearer '+self.accessToken}
    request = requests.get(self.api + '/spotify/getTrack/'+track, headers=headers)
    return request.json()['preview_url']

  def playTrack(self, trackUrl):  
    self.playing = True;
    urllib.urlretrieve(trackUrl, "song.mp3")
    player = subprocess.Popen(["omxplayer",'song.mp3'], stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    player.wait()
    self.playing = False;
    # player.stdin.write("q")

	
