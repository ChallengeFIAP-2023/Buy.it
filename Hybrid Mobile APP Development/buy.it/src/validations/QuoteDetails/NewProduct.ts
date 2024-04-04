import * as yup from 'yup';

export const NewProductFormSchema = yup.object().shape({
  nome: yup.string().required('Nome do produto é obrigatório.'),
  marca: yup.string().optional(),
  cor: yup.string().optional(),
  tamanho: yup.string().optional(),
  material: yup.string().optional(),
  obervacoes: yup.string().optional(),
});
