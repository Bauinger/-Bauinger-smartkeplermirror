<template>
  <div class='row'>
    <div class="col-sm">
      <img src="./assets/logo.png" alt="jku-logo"/>
    </div>
    <div class="col-sm">
      {{temperature + " ° C"}}
    </div> 
    <div class="col-sm">
      {{date.toLocaleString()}} <br>
      <digital-clock/>
    </div>    
  </div>
</template>
 
<script>
import DigitalClock from "vue-digital-clock";
 
export default {
  name: 'Header',
  components: {
    DigitalClock
  },
  data: function() {
    return {
      date : '',
      temperature: '',
      errored: false,
      timer : '',
    };
  },
  mounted() {
    this.loadActualDateandTemparature();
    this.timer = setInterval(this.loadActualDateandTemparature, 3600000)
  },
  beforeDestroy (){
    this.cancelAutoUpdate();
  },
  methods: {
    async loadActualDateandTemparature() {
      var today = new Date();
      var dd = String(today.getDate()).padStart(2, '0');
      var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
      var yyyy = today.getFullYear();
      this.date = dd + '.' + mm + '.' + yyyy;
      let apiUrl = 'https://fcc-weather-api.glitch.me/api/current?lat=48&lon=14';
      let res= await this.axios.get(apiUrl)
                               .catch(error => {
                                        console.log(error)
                                        this.errored = true;
                                     });
      let temp = res.data.main.temp;
      console.log(temp);
      this.temperature = Math.round(temp);
    },
    cancelAutoUpdate () {
      clearInterval(this.timer);
    }
  }
}
</script>