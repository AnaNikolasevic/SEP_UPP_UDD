<template>
  <div>
    <div
      v-for="(field, index) in formFieldsDTO.formFields"
      :key="index * 1.285"
    >
      <div class="mt-2" v-if="field.defaultValue != null">
        {{ field.defaultValue }}
      </div>
      <div v-else-if="field.defaultValue == null">
        <div class="mt-2" v-if="field.type.name == 'string'">
          <div v-if="field.defaultValue == null">
            <v-text-field
              :label="field.id"
              v-model="field.fieldValue"
              :rules="rules(field)"
            ></v-text-field>
          </div>
        </div>
      </div>
      <div v-if="field.type.name == 'multiEnum_type'">
        <v-combobox
          :items="Object.keys(field.type.values)"
          :label="field.label"
          v-model="field.fieldValue"
          outlined
          dense
          multiple
          :rules="rules(field)"
        ></v-combobox>
      </div>
      <div v-if="field.type.name == 'file_upload'">
        <v-file-input
          label="Choose pdf"
          truncate-length="15"
          v-model="field.fieldValue"
          :rules="rules(field)"
        ></v-file-input>
      </div>
      <div v-if="field.type.name == 'file_view'">
        <v-btn title="Open" color="primary" :href="field.value.value" download>
          <v-icon>mdi-file</v-icon>
        </v-btn>
      </div>
      <div v-if="field.type.name == 'multiEnum_comments'">
        <v-list-item v-for="(value, i) in field.type.values" :key="i">
          <v-list-item-content v-text="value"> </v-list-item-content>
        </v-list-item>
      </div>
      <div v-if="field.type.name == 'boolean'">
        <v-btn icon v-on="{ ...tooltip }" color="primary" @click="accept()">
          <v-icon>mdi-check</v-icon>
        </v-btn>
        <v-btn icon v-on="{ ...tooltip }" color="primary" @click="deny()">
          <v-icon>mdi-close</v-icon>
        </v-btn>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    formFieldsDTO: {},
    pageName: {},
  },
  methods: {
    accept() {
      this.$emit("accepted", this.formFieldsDTO, this.formFieldsDTO.formFields);
    },
    deny() {
      this.$emit("denied", this.formFieldsDTO, this.formFieldsDTO.formFields);
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