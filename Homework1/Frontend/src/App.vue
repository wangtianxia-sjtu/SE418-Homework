<template>
  <div id="app">
    <img src="./assets/logo.png">
    <div>
      <h1>Word Ladder Reimplementation with Vue.js and Spring Boot </h1>
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
      <el-button type="primary" :disabled="!start_valid || !end_valid || start == end" style="margin-top:3%;" @click="findLadder1">Go</el-button>
      <br/>
      <p v-show="start == end">
        Two words should be different.
      </p>
      <p v-show="loading">
        loading
      </p>
      <p v-show="haveSearched">
      {{final_result}}
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'app',
  data () {
    return {
      start: 'code',
      end: 'data',
      reg: /^[A-Za-z]+$/,
      res: null,
      loading: false,
      haveSearched: false
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
        url: 'http://localhost:8964/search?start='+this.start_lower+'&end='+this.end_lower
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
