import * as yup from 'yup';

export const Step4FormSchema = yup.object().shape({
  valorProduto: yup
    .string()
    .required('Preço obrigatório.'),
});
