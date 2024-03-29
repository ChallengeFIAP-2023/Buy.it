import { ArrowRight } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { QuoteDetailsRoutes } from '..';

// Theme import
import theme from '@theme/index';

// Validation import
import { Step3FormSchema } from '@validations/QuoteDetails';

// Component import
import {
  Button,
  CustomSlider,
  DecreasingContainer,
  DefaultComponent,
  WrapperPage,
} from '@components/index';

// Style import
import { LightText, LightBoldText } from './styles';
import { ScrollableContent } from '@global/styles/index';
import { useState } from 'react';
import { useQuoteDetails } from '@hooks/useQuoteDetails';

interface Step3Form {
  prioridadeEntrega: number;
  prioridadeQualidade: number;
  prioridadePreco: number;
}

export const Step3: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuoteDetailsRoutes, 'Step3'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  const { setValue } = useForm<Step3Form>({
    resolver: yupResolver(Step3FormSchema),
    defaultValues: {},
  });

  const labelValues: { [key: number]: string } = {
    1: 'baixa',
    2: 'média',
    3: 'alta',
  };

  const { product, quote } = useQuoteDetails();

  const [deliveryPriority, setDeliveryPriority] = useState<string>();
  const [pricePriority, setPricePriority] = useState<string>();
  const [qualityPriority, setQualityPriority] = useState<string>();

  type keyTypes =
    | 'prioridadeEntrega'
    | 'prioridadeQualidade'
    | 'prioridadePreco';

  function handleSetValues(
    value: number,
    key: keyTypes,
    setLabel: React.Dispatch<React.SetStateAction<string | undefined>>,
  ) {
    const integerValue = Math.round(value);
    const label = `${integerValue}: importância ${labelValues[integerValue]}`;
    setLabel(label);
    return setValue(key, integerValue);
  }

  function handleDeliveryPriority(value: number) {
    handleSetValues(value, 'prioridadeEntrega', setDeliveryPriority);
  }

  function handlePricePriority(value: number) {
    handleSetValues(value, 'prioridadePreco', setPricePriority);
  }

  function handleQualityPriority(value: number) {
    handleSetValues(value, 'prioridadeQualidade', setQualityPriority);
  }

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: 'O que é prioridade na cotação?',
            subtitle: 'Passo 3 de 5',
          }}
          key="default-component-quote-details"
        />

        <DecreasingContainer scrollable>
          <LightText>
            A nota de cada item está relacionada à sua importancia, onde 1
            significa <LightBoldText> pouco importante</LightBoldText> e 3
            significa <LightBoldText>muito importante</LightBoldText>
          </LightText>

          <CustomSlider
            label="Entrega rápida"
            value={Number(deliveryPriority) ?? 0}
            minimumValue={1}
            maximumValue={3}
            step={1}
            onValueChange={handleDeliveryPriority}
          />

          <CustomSlider
            label="Qualidade"
            value={Number(qualityPriority) ?? 0}
            minimumValue={1}
            maximumValue={3}
            step={1}
            onValueChange={handleQualityPriority}
          />

          <CustomSlider
            label="Preço baixo"
            value={Number(pricePriority) ?? 0}
            minimumValue={1}
            maximumValue={3}
            step={1}
            onValueChange={handlePricePriority}
          />
        </DecreasingContainer>
      </ScrollableContent>

      <Button
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={() => navigation.navigate('Step4')}
      />
    </WrapperPage>
  );
};
