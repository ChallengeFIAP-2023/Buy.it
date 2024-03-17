import * as yup from 'yup';

export const Step3FormSchema = yup.object().shape({
  prioridadeEntrega: yup
    .number()
    .required('Prioridade de entrega obrigatória.')
    .positive('Deve ser um número positivo.')
    .integer('Deve ser um número inteiro.')
    .max(3, 'Prioridade máxima é 3')
    .typeError('Informe a prioridade de entrega'),
  prioridadeQualidade: yup
    .number()
    .required('Prioridade de qualidade obrigatória.')
    .positive('Deve ser um número positivo.')
    .integer('Deve ser um número inteiro.')
    .max(3, 'Prioridade máxima é 3')
    .typeError('Informe a prioridade de qualidade'),
  prioridadePreco: yup
    .number()
    .required('Prioridade de preço obrigatória.')
    .positive('Deve ser um número positivo.')
    .integer('Deve ser um número inteiro.')
    .max(3, 'Prioridade máxima é 3')
    .typeError('Informe a prioridade de preço'),
});
