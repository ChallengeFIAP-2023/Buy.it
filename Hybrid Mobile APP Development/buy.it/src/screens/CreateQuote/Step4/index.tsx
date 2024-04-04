import { ArrowRight } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { useForm, SubmitHandler, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import React, { Fragment, useLayoutEffect } from 'react';

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
import { useCreateQuote } from '@hooks/useCreateQuote';
import { useAuth } from '@hooks/useAuth';

// Utils import
import { formatCurrency } from '@utils/formatStrings';
import { toMaskedCurrency, unMask, unMaskCurrency } from '@utils/masks';
import Toast from 'react-native-toast-message';

interface Step4Form {
  valorProduto: string;
}

interface Message {
  productPrices: ProductPrices;
}

const EM_ANDAMENTO = 1;

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
    loading
  } = useCreateQuote();

  const { user } = useAuth();

  const onSubmit: SubmitHandler<Step4Form> = data => {
    const dataAbertura = new Date().toISOString();
    const idStatus = EM_ANDAMENTO;
    // const idComprador = user.id as unknown as number;
    const idComprador = 1;

    const valorProduto = unMaskCurrency(data.valorProduto);

    if(valorProduto <= 0) 
      return Toast.show({
        type: "error",
        text1: "Erro",
        text2: "Insira um valor válido!"
      })

    const finalQuery: QuoteQuery = { 
      ...quote, dataAbertura, idStatus, idComprador, valorProduto
    }

    handleNewQuote(finalQuery);
  };

  useLayoutEffect(() => {
    fetchProductPrices(quote.idProduto);
  }, []);

  if (loading || !productPrices.produto) return <Loading />

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: `Quanto você quer pagar em cada ${productPrices.produto.nome}?`,
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
                  onChangeText={text => onChange(toMaskedCurrency(text))}
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
                setValue('valorProduto', 
                toMaskedCurrency(String(productPrices.avgValor?.toFixed(2))))
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

function Message ({ productPrices }: Message) {
    return (
      <Fragment>
      {productPrices.avgValor && productPrices.minValor ? (
        <Fragment>
          <LightText>
            O valor médio de cotações para{' '}
            <LightBoldText>{productPrices.produto.nome}</LightBoldText> é {' '}
            {formatCurrency(productPrices.avgValor)} por unidade.
          </LightText>
          <LightText>
            O menor preço já pago em {productPrices.produto.nome}{' '}
            foi {formatCurrency(productPrices.minValor)}.
          </LightText>
        </Fragment>
        ) : (
          <LightText>
            Infelizmente ainda não possuímos uma média de valores anteriores para
            <LightBoldText> {productPrices.produto && productPrices.produto.nome}</LightBoldText>. 
            Quando tivermos você receberá a sugestão.
          </LightText>
        )
      }

      <LightText>Quanto você quer pagar?</LightText>
    </Fragment>
  )
}