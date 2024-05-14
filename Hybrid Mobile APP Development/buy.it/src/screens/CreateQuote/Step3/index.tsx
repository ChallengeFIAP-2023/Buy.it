import { ArrowRight } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { useState } from 'react';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { CreateQuoteRoutes } from '..';

// Theme import
import theme from '@theme/index';

// Component import
import {
  Button,
  CustomSlider,
  DecreasingContainer,
  DefaultComponent,
  WrapperPage,
} from '@components/index';

// Style import
import { LightText, LightBoldText, Label } from './styles';
import { ScrollableContent } from '@global/styles/index';

//Hook import 
import { useQuote } from '@hooks/useQuote';

type Priority = {
  label: string;
  value: number;
}

type Priorities = {
  delivery: Priority;
  quality: Priority;
  price: Priority;
}

type keyTypes =
| 'delivery'
| 'quality'
| 'price';

export const Step3: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<CreateQuoteRoutes, 'Step3'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {

  const { setQuote } = useQuote();

  const onSubmit = () => {
    const prioridadeEntrega = priorities.delivery.value;
    const prioridadeQualidade = priorities.quality.value;
    const prioridadePreco = priorities.price.value;
    
    setQuote(prevQuote => ({ ...prevQuote, prioridadeEntrega, prioridadeQualidade, prioridadePreco }));

    return navigation.navigate('Step4');
  };

  const labelValues: { [key: number]: string } = {
    1: 'baixa',
    2: 'média',
    3: 'alta',
  };

  const defaultPriorities = {
    delivery: {
      label: "1: importância baixa", value: 1 
    },
    quality: {
      label: "2: importância média", value: 2
    },
    price: {
      label: "3: importância média", value: 3
    },
  }

  const [priorities, setPriorities] = useState<Priorities>(defaultPriorities);

  function handleSetPriorities(value: number, key: keyTypes) {
    const integerValue = Math.round(value);
    const label = `${integerValue}: importância ${labelValues[integerValue]}`;

    const newValue = { label, value };
    setPriorities(prevPriorities => ({ ...prevPriorities, [key]: newValue }));

    handlePrioritiesChanged(value, key);
  }

  function handlePrioritiesChanged(value: number, key: keyTypes) {
    const prevPriorities = { ...priorities }
    delete prevPriorities[key];

    // TODO: verificar o valor alterado com "prevPriorities" pra alterar também as outras prioridades para que não tenham repetidos
  }

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: 'O que é prioridade na cotação?',
            subtitle: 'Passo 3 de 4',
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
            value={priorities.delivery.value}
            minimumValue={1}
            maximumValue={3}
            step={1}
            onValueChange={(value: number) => handleSetPriorities(value, 'delivery')}
          />
          <Label>{priorities.delivery.label}</Label>

          <CustomSlider
            label="Qualidade"
            value={priorities.quality.value}
            minimumValue={1}
            maximumValue={3}
            step={1}
            onValueChange={(value: number) => handleSetPriorities(value, 'quality')}
          />
          <Label>{priorities.quality.label}</Label>

          <CustomSlider
            label="Preço baixo"
            value={priorities.price.value}
            minimumValue={1}
            maximumValue={3}
            step={1}
            onValueChange={(value: number) => handleSetPriorities(value, 'price')}
          />
          <Label>{priorities.price.label}</Label>
        </DecreasingContainer>
      </ScrollableContent>

      <Button
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={onSubmit}
      />
    </WrapperPage>
  );
};
