import { ArrowRight, Minus, Plus } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { useForm, SubmitHandler, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { CreateQuoteRoutes } from '..';

// Theme import
import theme from '@theme/index';

// Validation import
import { Step4FormSchema } from '@validations/QuoteDetails';

// Component import
import {
  Button,
  DecreasingContainer,
  DefaultComponent,
  Input,
  WrapperPage,
} from '@components/index';

// Style import
import { ScrollableContent, Fieldset } from '@global/styles/index';

// Hook import
import { useCreateQuote } from '@hooks/useCreateQuote';

interface Step4Form {
  marca?: string;
  cor?: string;
  tamanho?: string;
  material?: string;
  observacoes?: string;
}

export const Step4: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<CreateQuoteRoutes, 'Step4'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm<Step4Form>({
    resolver: yupResolver(Step4FormSchema),
  });

  // Hook
  const { setProduct, handleNewProduct, product } = useCreateQuote();

  const onSubmit: SubmitHandler<Step4Form> = async data => {
    setProduct(prevProd => ({ ...prevProd, ...data }));

    console.debug(product);

    try {
      await handleNewProduct(product);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: 'Se preferir, especifique detalhes do produto',
            subtitle: 'Passo 4 de 5 (opcional)',
          }}
          key="default-component-quote-details"
        />

        <DecreasingContainer scrollable>
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
                  error={errors.marca?.message}
                />
              )}
            />
          </Fieldset>

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

          <Fieldset style={{ marginBottom: 130 }}>
            <Controller
              control={control}
              name="observacoes"
              render={({ field: { value, onChange } }) => (
                <Input
                  value={value}
                  onChangeText={onChange}
                  label="Observacoes"
                  placeholder="Informações complementares"
                  error={errors.observacoes?.message}
                  numberOfLines={3}
                />
              )}
            />
          </Fieldset>
        </DecreasingContainer>
      </ScrollableContent>

      <Button
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={handleSubmit(onSubmit)}
      />
    </WrapperPage>
  );
};
