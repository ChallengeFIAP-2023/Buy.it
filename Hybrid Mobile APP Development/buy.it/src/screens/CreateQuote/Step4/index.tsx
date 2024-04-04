import { ArrowRight } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { useForm, SubmitHandler, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import Toast from 'react-native-toast-message';
import { format } from 'date-fns';
import React from 'react';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { CreateQuoteRoutes } from '..';
import { QuoteQuery } from '@dtos/quote';

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
import { LightText, LightBoldText } from './styles';
import { ScrollableContent, Fieldset } from '@global/styles/index';

// Hook import
import { useCreateQuote } from '@hooks/useCreateQuote';
import { useAuth } from '@hooks/useAuth';

interface Step4Form {
  valorProduto: number;
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
    formState: { errors },
  } = useForm<Step4Form>({
    resolver: yupResolver(Step4FormSchema),
  });

  const { quote, handleNewQuote } = useCreateQuote();

  const { user } = useAuth();

  const onSubmit: SubmitHandler<Step4Form> = data => {
    const dataAbertura = new Date().toISOString();
    const idStatus = EM_ANDAMENTO;
    // const idComprador = user.id as unknown as number;
    const idComprador = 1;

    const finalQuery: QuoteQuery = { 
      ...quote, ...data, dataAbertura, idStatus, idComprador 
    }
    handleNewQuote(finalQuery);
  };

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: 'Quanto você quer pagar em cada caneta esferográfica?',
            subtitle: 'Passo 4 de 4',
          }}
          key="default-component-quote-details"
        />

        <DecreasingContainer scrollable>
          <LightText>
            O valor médio de cotações para{' '}
            <LightBoldText> caneta esferográfica </LightBoldText> é R$1,00 por
            unidade. O menor preço já pago em caneta esferográfica foi R$0,45.
          </LightText>

          <LightText>Quanto você quer pagar?</LightText>

          <Fieldset>
            <Controller
              control={control}
              name="valorProduto"
              render={({ field: { value, onChange } }) => (
                <Input
                  value={value?.toString()}
                  onChangeText={onChange}
                  label="Valor"
                  placeholder="R$ 0,50"
                  error={errors.valorProduto?.message}
                />
              )}
            />
          </Fieldset>

          <Button label="Utilizar preço sugerido" size="SM" />
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
