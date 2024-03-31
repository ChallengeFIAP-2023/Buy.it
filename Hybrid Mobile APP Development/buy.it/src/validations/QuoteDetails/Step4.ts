import * as yup from 'yup';

export const Step4FormSchema = yup.object().shape({
  marca: yup.string().optional(),
  cor: yup.string().optional(),
  tamanho: yup.string().optional(),
  material: yup.string().optional(),
  obervacoes: yup.string().optional(),
});
