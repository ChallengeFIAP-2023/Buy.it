import * as yup from "yup";

export const Step5FormSchema = yup.object().shape({
  senha: yup.string().required("Senha é obrigatório.")
});
