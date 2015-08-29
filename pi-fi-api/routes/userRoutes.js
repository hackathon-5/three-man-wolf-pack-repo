'use strict';

var userController = require('../controllers/userController');

function UserRoutes(api) {
  api.post('/api/user', userController.createUser);
  api.get('/api/user/:userId', userController.getUserById);
  api.put('/api/user', userController.updateUser);
  api.put('/api/user/track', userController.saveTrack);
  api.get('/api/user/:bluetooth', userController.getUserByBluetooth);
}

module.exports.routes = UserRoutes;