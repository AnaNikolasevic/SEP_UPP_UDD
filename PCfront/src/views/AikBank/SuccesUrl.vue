<template>
  <div>
    <h1>Success!</h1>
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
          .put("http://localhost:8082/orderRequest/edit/" + this.orderId + "/" + "SUCCESS")
          .then(() => {
            console.log("USPESNOO");

          })
          .catch((error) => {
            console.log(error);
          });
  }
};
</script>