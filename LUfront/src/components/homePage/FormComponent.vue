<template>
  <div>
    <div v-for="field in formFields" :key="field.id">
      <v-text-field
        :label="field.id"
        v-if="field.type.name == 'string'"
        v-model="field.fieldValue"
        :rules="rules(field)"
      ></v-text-field>
      <v-combobox
        v-if="field.type.name == 'enum'"
        :items="Object.keys(field.type.values)"
        :label="field.label"
        v-model="field.fieldValue"
        outlined
        dense
        :rules="rules(field)"
      ></v-combobox>
      <v-checkbox
        v-if="field.type.name == 'boolean'"
        v-model="field.fieldValue"
        :label="field.label"
      ></v-checkbox>
      <v-combobox
        v-if="field.type.name == 'multiEnum_genres'"
        :items="Object.keys(field.type.values)"
        :label="field.label"
        v-model="field.fieldValue"
        outlined
        dense
        :rules="rules(field)"
        multiple
      ></v-combobox>
      <div v-if="field.type.name == 'file_upload'">
            <v-file-input
               label="Upload pdf"
               truncate-length="15"
               v-model="field.fieldValue"
            ></v-file-input>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    formFields: {},
  },
  data: () => ({
      imageData: null,
      uploadValue: 0,
      picture: null,
  }),
  methods: {
    previewImage(event) {
      this.uploadValue=0;
      this.picture=null;
      this.imageData = event.target.files[0];
    },
    rules(field) {
      const rules = [];
      field.validationConstraints.forEach((constraint) => {
        if (constraint.name == "required") {
          const rule = (v) => !!v || "This field is required";
          rules.push(rule);
        }
        if (constraint.name == "minlength") {
          const rule = (v) =>
            (v || "").length >= constraint.configuration ||
            `A minimum of ${constraint.configuration} characters is required`;
          rules.push(rule);
        }
        if (constraint.name == "maxlength") {
          const rule = (v) =>
            (v || "").length <= constraint.configuration ||
            `A maximum of ${constraint.configuration} characters is allowed`;
          rules.push(rule);
        }
      });
      return rules;
    },
  },
};
</script>

<style scoped>
.cardBorderColor {
  border-left: 1px solid #9575cd;
  border-top: 1px solid #9575cd;
  border-right: 1px solid #9575cd;
  border-bottom: 1px solid #9575cd;
}
.detailsBorderColor {
  border-left: 2px solid #9575cd;
  border-top: 2px solid #9575cd;
  border-right: 2px solid #9575cd;
  border-bottom: 2px solid #9575cd;
}
</style>