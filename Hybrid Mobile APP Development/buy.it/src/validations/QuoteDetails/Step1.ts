import * as yup from 'yup';

export const Step1FormSchema = yup.object().shape({
  idsTags: yup
    .array()
    .of(yup.number().required())
    .min(1, 'Selecione uma tag.')
    .required('Selecione uma tag.'),
  idDepartamento: yup.number().required('Departamento obrigatório.'),
});
