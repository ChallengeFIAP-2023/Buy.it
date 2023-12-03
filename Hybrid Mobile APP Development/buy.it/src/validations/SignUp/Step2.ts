import * as yup from "yup";

export const Step2FormSchema = yup.object().shape({
  nome: yup.string().required("Nome é obrigatório."),
  email:
    yup.string().email("Informe um email válido").required("Email é obrigatório."),
  cnpj:
    yup.string().required("CNPJ é obrigatório.").min(18, "CNPJ é obrigatório."),
});
