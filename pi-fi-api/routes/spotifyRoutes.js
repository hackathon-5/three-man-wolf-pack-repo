'use strict';

var spotifyController = require('../controllers/spotifyController');

function SpotifyRoutes(api) {
  api.get('/api/spotify/login', spotifyController.login);
  api.get('/api/spotify/callback', spotifyController.callback);
  api.get('/api/spotify/refresh_token', spotifyController.refreshToken);
}

module.exports.routes = SpotifyRoutes;