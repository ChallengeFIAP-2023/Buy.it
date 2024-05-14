import { ArrowRight, Minus, Plus } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { useForm, SubmitHandler, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import React, { useLayoutEffect, useState } from 'react';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { CreateQuoteRoutes } from '..';
import { TFlatList } from 'react-native-input-select/lib/typescript/types/index.types';

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
import { CustomModal } from '@components/Modal';
import { NewProduct } from './NewProduct';

//Hook import
import { useQuote } from '@hooks/useQuote';

interface Step2Form {
  idProduto: number;
  quantidadeProduto: number;
}

const deadlineOptions: TFlatList = [
  { label: 'Rápido (3 dias)', value: 3 },
  { label: 'Padrão (7 dias)', value: 7 },
  { label: 'Estendido (2 semanas)', value: 14 },
  { label: 'Sem prioridade', value: 0 },
];

const PRODUCT_QUANTITY = 15;

export const Step2: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<CreateQuoteRoutes, 'Step2'>,
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
    defaultValues: {
      quantidadeProduto: 5,
    },
  });

  const { setQuote, products, fetchProducts } = useQuote();

  useLayoutEffect(() => {
    fetchProducts();
  }, []);

  const onSubmit: SubmitHandler<Step2Form> = data => {
    const prazo = deadline as number;
    setQuote(prevQuote => ({ ...prevQuote, prazo, ...data }));

    return navigation.navigate('Step3');
  };

  function handleSubtract(value: number) {
    const badValueConditions =
      isNaN(value) || value <= 0 || value - PRODUCT_QUANTITY < 5;
    if (badValueConditions) return setValue('quantidadeProduto', 5);

    return setValue('quantidadeProduto', value - PRODUCT_QUANTITY);
  }

  function handleSum(value: number) {
    if (isNaN(value)) return setValue('quantidadeProduto', 5);

    return setValue('quantidadeProduto', Number(value) + PRODUCT_QUANTITY);
  }

  const [deadline, setDeadline] = useState(deadlineOptions[0].value);
  const [isModalVisible, setModalVisible] = useState(false);

  const toggleModal = () => {
    setModalVisible(!isModalVisible);
  };

  const productsOptions: TFlatList = products.map(item => ({
    label: `${item.nome} ${item.marca || ''} ${item.cor || ''} ${
      item.material || ''
    } ${item.tamanho || ''}`,
    value: item.id,
  }));

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: 'Principais informações do produto',
            subtitle: 'Passo 2 de 4',
          }}
          key="default-component-quote-details"
        />

        <DecreasingContainer scrollable>
          <Fieldset>
            <Controller
              control={control}
              name="idProduto"
              render={({ field: { value, onChange } }) => (
                <CustomDropdown
                  label="Produto"
                  isSearchable
                  placeholder="Selecione o produto que deseja cotar"
                  options={productsOptions}
                  selectedValue={value}
                  onValueChange={onChange}
                  error={errors.idProduto?.message}
                  listFooterComponent={
                    <Button
                      label="Novo produto"
                      size="SM"
                      style={{ margin: 15 }}
                      onPress={toggleModal}
                    />
                  }
                />
              )}
            />
          </Fieldset>

          <Fieldset>
            <Controller
              control={control}
              name="quantidadeProduto"
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
                        placeholder="Quantidade"
                        keyboardType="number-pad"
                        error={errors.quantidadeProduto?.message}
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
              options={deadlineOptions}
              selectedValue={deadline}
              onValueChange={(value: number) => setDeadline(value)}
              listControls={{
                emptyListMessage: 'Nenhum tipo de prazo encontrado',
              }}
            />
          </Fieldset>
        </DecreasingContainer>
      </ScrollableContent>

      <CustomModal
        modalProps={{ isVisible: isModalVisible }}
        onClose={toggleModal}
      >
        <NewProduct toggleModal={toggleModal} />
      </CustomModal>

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
