import * as yup from 'yup';

export const Step4FormSchema = yup.object().shape({
  preco: yup
    .number()
    .required('Preço obrigatório.')
    .positive('Deve ser um número positivo.')
    .typeError('Informe o preço.'),
});
