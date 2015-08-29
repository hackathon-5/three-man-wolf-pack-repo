'use strict';

// Load required packages
var responseUtil = require('../util/responseUtil');
var fs = require('fs');

var DeviceController = {

  log: function(req, res) {
    var log = req.body.deviceId + ',' + req.body.time + "\n";
    fs.appendFile('deviceLog.csv', log, function (err) {
      if(err) {
        return responseUtil.handleInternalError(err, "Failed to save log");
      } else {
        return responseUtil.handleSuccess(res, "Log saved");
      }
    });
    
  }
};

module.exports = DeviceController;