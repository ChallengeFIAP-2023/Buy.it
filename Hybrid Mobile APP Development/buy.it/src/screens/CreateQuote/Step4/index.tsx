import { ArrowRight } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { useForm, SubmitHandler, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import React, { Fragment, useLayoutEffect } from 'react';
import Toast from 'react-native-toast-message';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { CreateQuoteRoutes } from '..';
import { QuoteQuery } from '@dtos/quote';
import { ProductPrices } from '@dtos/product';

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
  Loading,
  WrapperPage,
} from '@components/index';

// Style import
import { LightText, LightBoldText } from './styles';
import { ScrollableContent, Fieldset } from '@global/styles/index';

// Hook import
import { NavigationProp, useQuote } from '@hooks/useQuote';
import { useAuth } from '@hooks/useAuth';

// Utils import
import { toMaskedCurrency, unMaskCurrency } from '@utils/masks';
import { STATUS_OPTIONS } from '@utils/statusOptions';
import { format } from 'date-fns';

interface Step4Form {
  valorProduto: string;
}

interface Message {
  productPrices: ProductPrices;
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
    setValue,
    formState: { errors },
  } = useForm<Step4Form>({
    resolver: yupResolver(Step4FormSchema),
  });

  const { 
    quote, 
    handleNewQuote, 
    fetchProductPrices, 
    productPrices,
    fetchQuotesByBuyer,
    loading 
  } = useQuote();

  const { user } = useAuth();

  const onSubmit: SubmitHandler<Step4Form> = data => {
    const dataAbertura = format(new Date(), 'dd-MM-yyyy');
    const idStatus = STATUS_OPTIONS.inProgress;
    const idComprador = Number(user.id);

    const valorProduto = unMaskCurrency(data.valorProduto);

    if (valorProduto <= 0)
      return Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Insira um valor válido!',
      });

    const finalQuery: QuoteQuery = {
      ...quote,
      dataAbertura,
      idStatus,
      idComprador,
      valorProduto,
    };

    handleNewQuote(finalQuery, navigation as NavigationProp);
    fetchQuotesByBuyer(user.id);
  };

  useLayoutEffect(() => {
    fetchProductPrices(quote.idProduto);
  }, []);

  if (loading || !productPrices.produto) return <Loading />;

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: `Quanto você quer pagar em cada ${productPrices.produto.nome}?`,
            highlightedText: `${productPrices.produto.nome}?`,
            subtitle: 'Passo 4 de 4',
          }}
          key="default-component-quote-details"
        />

        <DecreasingContainer>
          <Message productPrices={productPrices} />

          <Fieldset>
            <Controller
              control={control}
              name="valorProduto"
              render={({ field: { value, onChange } }) => (
                <Input
                  value={value?.toString()}
                  onChangeText={text => onChange(toMaskedCurrency(text, true))}
                  label="Valor"
                  placeholder="R$ 0,50"
                  keyboardType="numeric"
                  error={errors.valorProduto?.message}
                />
              )}
            />
          </Fieldset>

          {productPrices.avgValor && (
            <Button
              label="Utilizar preço sugerido"
              size="SM"
              onPress={() =>
                setValue(
                  'valorProduto',
                  toMaskedCurrency(
                    Number(productPrices.avgValor).toFixed(2),
                    true,
                  ),
                )
              }
            />
          )}
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

function Message({ productPrices }: Message) {
  return (
    <Fragment>
      {productPrices.avgValor && productPrices.minValor ? (
        <Fragment>
          <LightText>
            O valor médio de cotações para{' '}
            <LightBoldText>{productPrices.produto.nome}</LightBoldText> é{' '}
            {toMaskedCurrency(productPrices.avgValor.toFixed(2), true)} por
            unidade.
          </LightText>
          <LightText>
            O menor preço já pago em {productPrices.produto.nome} foi{' '}
            {toMaskedCurrency(productPrices.minValor.toFixed(2), true)}.
          </LightText>
        </Fragment>
      ) : (
        <LightText>
          Infelizmente ainda não possuímos uma média de valores para
          <LightBoldText>
            {' '}
            {productPrices.produto && productPrices.produto.nome}
          </LightBoldText>
          . Quando tivermos você a receberá como sugestão.
        </LightText>
      )}

      <LightText>Por quanto R$ você gostaria de comprar?</LightText>
    </Fragment>
  );
}
