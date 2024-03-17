import * as yup from 'yup';

export const Step5FormSchema = yup.object().shape({
  marca: yup.string(),
  cor: yup.string(),
  tamanho: yup.string(),
  material: yup.string(),
  obervacoes: yup.string(),
});
