import { ArrowRight } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { useForm, SubmitHandler, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import Slider from '@react-native-community/slider';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { QuoteDetailsRoutes } from '..';

// Theme import
import theme from '@theme/index';

// Validation import
import { Step4FormSchema } from '@validations/QuoteDetails';

// Component import
import {
  Button,
  CustomSlider,
  DecreasingContainer,
  DefaultComponent,
  Input,
  WrapperPage,
} from '@components/index';

// Style import
import {
  LightText,
  LightBoldText,
} from './styles';
import { ScrollableContent, Fieldset } from '@global/styles/index';

interface Step4Form {
  preco: number;
}

export const Step4: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuoteDetailsRoutes, 'Step4'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  const {
    control,
    formState: { errors },
    setValue,
  } = useForm<Step4Form>({
    resolver: yupResolver(Step4FormSchema),
    defaultValues: {},
  });

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: 'Quanto você quer pagar em cada caneta esferográfica?',
            subtitle: 'Passo 4 de 5',
          }}
          key="default-component-quote-details"
        />

        <DecreasingContainer scrollable>
          <LightText>
          O valor médio de cotações para <LightBoldText> caneta esferográfica </LightBoldText> é R$1,00 por unidade. O menor preço já pago em caneta esferográfica foi R$0,45.  <LightBoldText>muito importante</LightBoldText>
          </LightText>

          <LightText>
            Quanto você quer pagar?
          </LightText>

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

          <Button
            label="Utilizar preço sugerido"
            size="SM"
          />

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
