<template>
  <div>
    <h1>Your payment was canceled or expired</h1>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      paymentTypes: [
        {
          name: "",
        }
      ],
      choosenType: "",
      token: "",
      proba: {},
    };
  },
  methods: {
    
  },
  mounted() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const id = urlParams.get('orderId');
    axios
      .put("http://localhost:8089/cancel/" + id)
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.log(error);
      });
  }
};
</script>