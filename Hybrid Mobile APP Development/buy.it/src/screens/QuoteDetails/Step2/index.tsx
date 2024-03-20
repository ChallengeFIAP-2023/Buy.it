import { ArrowRight, Minus, Plus } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { useForm, SubmitHandler, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import { TFlatList } from 'react-native-input-select/lib/typescript/types/index.types';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { QuoteDetailsRoutes } from '..';

// Theme import
import theme from '@theme/index';

// Validation import
import { Step2FormSchema } from '@validations/QuoteDetails';

// Component import
import {
  Button,
  CustomDropdown,
  DecreasingContainer,
  DefaultComponent,
  Input,
  WrapperPage,
} from '@components/index';

// Style import
import {
  InputLabel,
  QuantityButton,
  QuantityContainer,
  InputWrapper,
} from './styles';
import { ScrollableContent, Fieldset, Flex } from '@global/styles/index';

interface Step2Form {
  nomeProduto: string;
  quantidade: number;
  prazo: string;
}

const deadlineOptions: TFlatList = [
  { label: 'Rápido (3 dias)', value: 'quick' },
  { label: 'Padrão (7 dias)', value: 'standard' },
  { label: 'Estendido (2 semanas)', value: 'extended' },
  { label: 'Sem prioridade', value: 'no-priority' },
];

const PRODUCT_QUANTITY = 15;

export const Step2: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuoteDetailsRoutes, 'Step2'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  const {
    control,
    handleSubmit,
    formState: { errors },
    setValue,
  } = useForm<Step2Form>({
    resolver: yupResolver(Step2FormSchema),
    defaultValues: {},
  });

  const onSubmit: SubmitHandler<Step2Form> = data => {
    return navigation.navigate('Step3');
  };

  function handleSubtract(value: number) {
    const badValueConditions = isNaN(value) || value <= 0 || (value - PRODUCT_QUANTITY < 5);
    if (badValueConditions) return setValue('quantidade', 5);

    return setValue('quantidade', value - PRODUCT_QUANTITY);
  }

  function handleSum(value: number) {
    if (isNaN(value)) return setValue('quantidade', 5);

    return setValue('quantidade', Number(value) + PRODUCT_QUANTITY);
  }

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: 'Principais informações do produto',
            subtitle: 'Passo 2 de 5',
          }}
          key="default-component-quote-details"
        />

        <DecreasingContainer scrollable>
          <Fieldset>
            <Controller
              control={control}
              name="nomeProduto"
              render={({ field: { value, onChange } }) => (
                <Input
                  value={value}
                  onChangeText={onChange}
                  label="Nome do produto"
                  placeholder="Caneta esferográfica"
                  error={errors.nomeProduto?.message}
                />
              )}
            />
          </Fieldset>

          <Fieldset>
            <Controller
              control={control}
              name="quantidade"
              render={({ field: { value, onChange } }) => (
                <QuantityContainer>
                  <InputLabel>Quantidade</InputLabel>

                  <Flex>
                    <QuantityButton onPress={() => handleSubtract(value)}>
                      <Minus color={theme.COLORS.WHITE} size={15} />
                    </QuantityButton>

                    <InputWrapper>
                      <Input
                        value={value?.toString()}
                        onChangeText={onChange}
                        placeholder="5"
                        keyboardType="number-pad"
                        error={errors.quantidade?.message}
                      />
                    </InputWrapper>

                    <QuantityButton onPress={() => handleSum(value)}>
                      <Plus color={theme.COLORS.WHITE} size={15} />
                    </QuantityButton>
                  </Flex>
                </QuantityContainer>
              )}
            />
          </Fieldset>

          <Fieldset>
            <CustomDropdown
              label="Prazo de recebimento"
              placeholder="3 dias"
              options={deadlineOptions}
              selectedValue={deadlineOptions[0].value}
              onValueChange={(value: []) => {}}
              listControls={{ emptyListMessage: 'Nenhuma tag encontrada' }}
            />
          </Fieldset>
        </DecreasingContainer>
      </ScrollableContent>

      <Button
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={() => navigation.navigate('Step3')}
      />
    </WrapperPage>
  );
};
