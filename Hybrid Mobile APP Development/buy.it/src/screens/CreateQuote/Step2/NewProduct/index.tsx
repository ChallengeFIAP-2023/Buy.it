import { useForm, SubmitHandler, Controller, Control, FieldErrors } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import { Fragment, useState } from 'react';
import { ArrowRight } from 'phosphor-react-native';

// Validation import
import { NewProductFormSchema } from '@validations/QuoteDetails';

// Component import
import { Button, DefaultComponent, Input } from '@components/index';
import { Fieldset } from '@global/styles/index';

// Hook import
import { useCreateQuote } from '@hooks/useCreateQuote';

// Theme import
import theme from '@theme/index';
import { ProductQuery } from '@dtos/product';

interface NewProductForm {
  nome: string;
  marca?: string;
  cor?: string;
  tamanho?: string;
  material?: string;
}

interface Step {
  control: Control<NewProductForm, any>
  errors: FieldErrors<NewProductForm>
}

export function NewProduct() {

  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm<NewProductForm>({
    resolver: yupResolver(NewProductFormSchema),
  });

  const { handleNewProduct, setProduct, product } = useCreateQuote();

  const onSubmit: SubmitHandler<NewProductForm> = data => {
    const finalQuery: ProductQuery = { ...product, ...data }
    console.debug(finalQuery);
    handleNewProduct(finalQuery);
  };

  const [step, setStep] = useState<number>(1);

  return (
    <Fragment>
      <DefaultComponent
        showHeader={step === 2}
        headerProps={{ 
          goBack: () => setStep(1),
          showLogo: false,
          noPadding: true
        }}
        highlightProps={{
          title: 'Novo produto',
          subtitle: `Passo ${step} de 2`,
          noPadding: true
        }}
        key="default-component-new-product"
      />

      {step == 1 ? (
        <Step1 control={control} errors={errors} />
      ) : (
        <Step2 control={control} errors={errors} />
      )}   

      {step === 1 ? (
        <Button
          label="Continuar"
          size="LG"
          icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
          onPress={() => setStep(2)}
        />
      ) : (
        <Button
          label="Criar Produto"
          size="LG"
          onPress={handleSubmit(onSubmit)}
        />
      )}   

    </Fragment>
  );
};

function Step1 ({ control, errors }: Step){
  return(
    <Fragment>
      <Fieldset>
        <Controller
          control={control}
          name="nome"
          render={({ field: { value, onChange } }) => (
            <Input
              value={value}
              onChangeText={onChange}
              label="Nome do produto"
              placeholder="Caneta esferográfica"
              error={errors.nome?.message} />
          )} />
      </Fieldset>
      
      <Fieldset>
        <Controller
          control={control}
          name="marca"
          render={({ field: { value, onChange } }) => (
            <Input
              value={value}
              onChangeText={onChange}
              label="Marca"
              placeholder="BIC"
              error={errors.marca?.message} />
          )} />
      </Fieldset>
    </Fragment>
  );
}

function Step2 ({ control, errors }: Step){
  return (
    <Fragment>
      <Fieldset>
        <Controller
          control={control}
          name="cor"
          render={({ field: { value, onChange } }) => (
            <Input
              value={value}
              onChangeText={onChange}
              label="Cor"
              placeholder="Azul"
              error={errors.cor?.message}
            />
          )}
        />
      </Fieldset>

      <Fieldset>
        <Controller
          control={control}
          name="tamanho"
          render={({ field: { value, onChange } }) => (
            <Input
              value={value}
              onChangeText={onChange}
              label="Tamanho"
              placeholder="Normal"
              error={errors.tamanho?.message}
            />
          )}
        />
      </Fieldset>

      <Fieldset>
        <Controller
          control={control}
          name="material"
          render={({ field: { value, onChange } }) => (
            <Input
              value={value}
              onChangeText={onChange}
              label="Material"
              placeholder="Plástico"
              error={errors.material?.message}
            />
          )}
        />
      </Fieldset>
    </Fragment>
  );
}