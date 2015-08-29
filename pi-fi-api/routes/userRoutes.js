'use strict';

var userController = require('../controllers/userController');

function UserRoutes(api) {
  api.post('/api/user', userController.createUser);
  api.get('/api/user/:userId', userController.getUserById);
  api.put('/api/user', userController.updateUser);
  api.put('/api/user/track', userController.saveTrack);
}

module.exports.routes = UserRoutes;