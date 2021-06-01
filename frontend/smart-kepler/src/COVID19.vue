<template>
  <span>
    <section v-if="errored">
            <p>We're sorry, we're not able to retrieve this information at the moment, please try back later</p>
    </section>
    <h3 id="titleCOVID19">COVID-19:</h3>
    <h5>Bestätigt:</h5>
    {{}}
  </span>
</template>

<script>
export default {
  name: 'COVID19',
  data: function() {
    return {
      recovered : 0,
      confirmed: '',
      dead : '',
      errored: false,
      timer : '',
    };
  },
  mounted() {
    this.loadCovidArea();
    this.timer = setInterval(this.loadCovidArea, 3600000)
  },
  beforeDestroy (){
    this.cancelAutoUpdate();
  },
  methods: {
    async loadCovidArea() {
      let apiUrl = 'https://covid-api.mmediagroup.fr/v1/cases?country=Austria';
     
       await this.axios.get(apiUrl)
      .then(x => {console.log(x.data)})
                                     .catch(error => {
                                         console.log(error)
                                         this.errored = true;
                                     });
    //   this.recovered = response.data.recovered;
    //   this.confirmed = response.data.confirmed;
    //   this.dead = response.data.dead;
    },
    cancelAutoUpdate () {
      clearInterval(this.timer);
    }
  }
}
</script>
<style>
  #titleCOVID19 {
    text-align: left;
  }
</style>