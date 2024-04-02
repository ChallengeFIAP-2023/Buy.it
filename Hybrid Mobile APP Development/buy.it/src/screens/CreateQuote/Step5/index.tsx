import { ArrowRight } from 'phosphor-react-native';
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
import { Step5FormSchema } from '@validations/QuoteDetails';

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

interface Step5Form {
  preco: number;
}

export const Step5: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<CreateQuoteRoutes, 'Step5'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  const {
    control,
    formState: { errors },
  } = useForm<Step5Form>({
    resolver: yupResolver(Step5FormSchema),
  });

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: 'Quanto você quer pagar em cada caneta esferográfica?',
            subtitle: 'Passo 5 de 5',
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
              name="preco"
              render={({ field: { value, onChange } }) => (
                <Input
                  value={value?.toString()}
                  onChangeText={onChange}
                  label="Valor"
                  placeholder="R$ 0,50"
                  error={errors.preco?.message}
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
        onPress={() => navigation.navigate('Step5')}
      />
    </WrapperPage>
  );
};