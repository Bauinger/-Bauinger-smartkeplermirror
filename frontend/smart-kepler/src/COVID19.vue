<template>
  <span>
    <section v-if="errored">
            <p>We're sorry, we're not able to retrieve this information at the moment, please try back later</p>
    </section>
    <h3 id="titleCOVID19">COVID-19:</h3>
    <h5>Bestätigt:</h5>
    {{recovered.toLocaleString('de')}}
    <h5>Tote:</h5>
    {{dead.toLocaleString('de')}}
    <h5>Genesene:</h5>
    {{recovered.toLocaleString('de')}}
  </span>
</template>

<script>
export default {
  name: 'COVID19',
  data: function() {
    return {
      recovered : '',
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
      let apiUrl = 'https://coronavirus-19-api.herokuapp.com/countries/austria';
      let res= await this.axios.get(apiUrl)
                               .catch(error => {
                                        console.log(error)
                                        this.errored = true;
                                     });
      let data = res.data;
      this.recovered = data.recovered;
      this.confirmed = data.cases;
      this.dead = data.deaths;
      console.log(data);
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