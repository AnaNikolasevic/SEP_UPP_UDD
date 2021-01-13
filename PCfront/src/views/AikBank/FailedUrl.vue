<template>
  <div>
    <h1> Your payment was canceled </h1>
    <h2>You don't have enough money</h2>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
        orderId : ''
    };
  },
  methods: {
      
  },
  mounted() {
    this.orderId = this.$route.params.id;
     axios
          .put("http://localhost:8082/orderRequest/edit/" + this.orderId + "/" + "FAILED")
          .then(() => {
            console.log("USPESNOO");

          })
          .catch((error) => {
            console.log(error);
          });
  }
};
</script>