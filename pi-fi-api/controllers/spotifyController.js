'use strict';

// Load required packages
var responseUtil = require('../util/responseUtil'),
    config = require('./../config'),
    querystring = require('querystring'),
    restify = require('restify'),
    redis = require("redis"),
    redisClient = redis.createClient(),
    querystring = require("querystring");

var SpotifyController = {

  login: function(req, res) {

    var client = restify.createStringClient({
      url: 'https://accounts.spotify.com',
      accept: 'application/json'
    });

    client.basicAuth(config.spotifyClientId, config.spotifySecret);

    var orginalres = res;

    client.post('/api/token', {grant_type: 'client_credentials'}, function(err, req, res, obj) {
      if(res.statusCode === 200) {
        redisClient.set("spotifyToken", JSON.parse(obj).access_token, redis.print);
        return responseUtil.handleSuccess(orginalres, obj);
      } else {
        return responseUtil.handleInternalError(orginalres, err);
      }
    });

  },

  search: function(req, res) {
    var originalRes = res;

    redisClient.get("spotifyToken", function (err, token) {
      if(token) {
        var client = restify.createJsonClient({
          url: 'https://api.spotify.com',
          headers: {
            Authorization: "Bearer "+token
          }
        });

        var searchparams = querystring.escape("linkin park");

        client.get('/v1/search?q='+searchparams+'&type=artist',  function(err, req, res, obj) {
          if(res.statusCode === 200) {
            return responseUtil.handleSuccess(originalRes, obj);
          } else {
            return responseUtil.handleInternalError(originalRes, err);
          }
        });

      } else {
        responseUtil.handleUnauthorizedRequest(originalRes, err);
      }
    });    
  },


  getTrack: function(req, res) {

    var originalRes = res;

    redisClient.get("spotifyToken", function (err, token) {
      if(token) {
        var client = restify.createJsonClient({
          url: 'https://api.spotify.com',
          headers: {
            Authorization: "Bearer "+token
          }
        });

        client.get('/v1/tracks/'+req.params.trackId,  function(err, req, res, obj) {
          if(res.statusCode === 200) {
            return responseUtil.handleSuccess(originalRes, obj);
          } else {
            return responseUtil.handleInternalError(originalRes, err);
          }
        });

      } else {
        responseUtil.handleUnauthorizedRequest(originalRes, err);
      }
    });    

  }
};

module.exports = SpotifyController