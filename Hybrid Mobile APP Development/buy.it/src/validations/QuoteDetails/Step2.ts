import * as yup from 'yup';

export const Step2FormSchema = yup.object().shape({
  produto: yup
    .number()
    .required("Selecione um produto!"),
  quantidade: yup
    .number()
    .required('Quantidade obrigatória.')
    .positive('Deve ser um número positivo.')
    .integer('Deve ser um número inteiro.')
    .min(5, 'Mín. 05 produtos')
    .typeError('Informe a quantidade'),
});
