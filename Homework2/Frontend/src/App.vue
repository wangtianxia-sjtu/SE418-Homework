<template>
  <div id="app">
    <img src="./assets/logo.png">
    <div>
      <h1>Word Ladder Reimplementation with Vue.js and Spring Boot</h1>
      <h1>A simple demo of Spring Security</h1>
      <el-row>
        <el-col :span="12">
          <div>
      Start from: <el-input v-model="start" placeholder="Input something..." style="width:15%;margin-top:3%;"></el-input>
      <br/>
      <p v-show="!start_valid && start != ''">
        Input should only contains English letters.
      </p>
      <p v-show="start == ''">
        Input something...
      </p>
      <br/>
      End with: <el-input v-model="end" placeholder="Input something..."  style="width:15%;"></el-input>
      <br/>
      <p v-show="!end_valid && end != ''">
        Input should only contains English letters.
      </p>
      <p v-show="end == ''">
        Input something...
      </p>
      <el-button type="primary" :disabled="!start_valid || !end_valid || start == end || start.length != end.length" style="margin-top:3%;" @click="findLadder1">Go</el-button>
      <br/>
      <p v-show="start == end">
        Two words should be different.
      </p>
      <p v-show="loading">
        loading
      </p>
      <p v-show="start.length != end.length">
        Two words must be of the same size.
      </p>
      <p v-show="haveSearched">
      {{final_result}}
      </p>
      </div>
        </el-col>
        <el-col :span="12">
          <div>
              Login
              <br/>
              Username: <el-input v-model="username" placeholder="Username" style="width:20%;margin-top:3%;"></el-input>
              <br/>
              Password: <el-input placeholder="Password" v-model="password" show-password style="width:20%;margin-top:3%;"></el-input>
              <br/>
              <el-button type="primary" :disabled="!username || !password" style="margin-top:3%;" @click="login">Login</el-button>
              <p>
                Username: user Password: password for a common user.<br/><br/>
                Username: admin Password: admin for an admin.
              </p>
              <p v-if="haveloggedin">
                You have successfully logged in.
              </p>
              <p v-else>
                You are not logged in.
              </p>
              <p v-show="networkError">
                Network Error.
              </p>
              <p v-show="failure">
                Incorrect username or password.
              </p>
              <el-button type="primary" style="margin-top:3%;" @click="getStatus">Get status (Only for admin user) </el-button>
              <p>
                {{status}}
              </p>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import qs from 'qs'
export default {
  name: 'app',
  data () {
    return {
      start: 'code',
      end: 'data',
      reg: /^[A-Za-z]+$/,
      res: null,
      loading: false,
      haveSearched: false,
      haveloggedin: false,
      username: '',
      password: '',
      networkError: false,
      failure: false,
      status: null
    }
  },
  computed: {
    start_lower: function () {
      return this.start.toLowerCase()
    },
    end_lower: function () {
      return this.end.toLowerCase()
    },
    start_valid: function () {
      return this.reg.test(this.start)
    },
    end_valid: function () {
      return this.reg.test(this.end)
    },
    final_result: function () {
      if(this.res == null)
        return ''
      try{
      if(this.res.length == 0)
        return 'No such ladder'
      }
      catch(Exception) {
        return ''
      }
      return this.res
    }
  },
  methods: {
    findLadder: function () {
      this.loading = true
      this.$axios
        .get('http://localhost:8964/search?start='+this.start_lower+'&end='+this.end_lower)
        .then((response) => {
          // this.res = response.status
          if (response.status !== 200) {
            this.res = 1
      } else {
            this.res = 0
      }
          })
        .finally(() => this.loading = false)
    },
    findLadder1: function () {
      this.loading = true
      this.$axios({
        method: 'get',
        url: 'http://localhost:8964/search?start='+this.start_lower+'&end='+this.end_lower,
        withCredentials: true
      }).then((response) => {
          // this.res = response.status
          if (response.status !== 200) {
            this.res = "Error!"
            this.haveSearched = true
      } else {
            this.res = response.data
            this.haveSearched = true
      }
          })
        .finally(() => this.loading = false)
    },
    getStatus: function () {
      this.$axios({
        method: 'get',
        url: 'http://localhost:8964/actuator/health',
        withCredentials: true
      }).then((response) => {
          // this.res = response.status
          if (response.status !== 200) {
            this.status = "Error!"
      } else {
            this.status = JSON.stringify(response.data)
      }
      }).catch((error) => {
        this.status = error
      })
    },
    login: function () {
      this.$axios({
        method: 'post',
        url: 'http://localhost:8964/login',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: qs.stringify({
          'username': this.username,
          'password': this.password
        }),
        withCredentials: true
      }).then((response) => {
          // this.res = response.status
          if (response.status !== 200) {
            this.networkError = true
      } else {
            if(response.data==1)
            {
              this.haveloggedin = true
              this.failure = false
              this.networkError = false
            }
            else
            {
              this.failure = true
              this.haveloggedin = false
            }
      }
          })
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
