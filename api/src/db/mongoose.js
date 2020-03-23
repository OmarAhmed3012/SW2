const mongoose = require('mongoose')
//const {MONGODB_URL} = require('../../config/constrains')  //process.env.MONGODB_URL
/////
mongoose.connect('mongodb://127.0.0.1:27017/TaskManager', {
    useNewUrlParser: true
})


// testing

const url = 'mongodb://127.0.0.1:27017/TaskManager'

const db = mongoose.connection
db.once('open', _ => {
  console.log('Database connected:', url)
})

db.on('error', err => {
  console.error('connection error:', err)
})