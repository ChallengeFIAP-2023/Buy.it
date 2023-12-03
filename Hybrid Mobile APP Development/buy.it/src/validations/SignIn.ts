import * as yup from "yup";

export const SignInFormSchema = yup.object().shape({
  email:
    yup
      .string()
      .email("Informe um email válido")
      .required("Email é obrigatório."),
  senha: yup.string().required("Senha é obrigatório."),
});
