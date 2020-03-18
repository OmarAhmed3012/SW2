const mongoose = require('mongoose')
//const {MONGODB_URL} = require('../../config/constrains')  //process.env.MONGODB_URL
/////
mongoose.connect('mongodb+srv://taskapp:omarahmed3012@cluster0-irrbg.mongodb.net/task-manager-api?retryWrites=true', {
    useNewUrlParser: true,
    useCreateIndex: true,
    useUnifiedTopology: true,
    useFindAndModify: false
})
