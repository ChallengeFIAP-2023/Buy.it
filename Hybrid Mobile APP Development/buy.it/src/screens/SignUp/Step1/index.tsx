import { useState } from 'react';
import { ArrowRight, IconProps } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { SignUpRoutes } from '..';

// Theme import
import theme from '@theme/index';

// Hook import
import { useSignUpForm } from '../../../hooks/useSignUpForm';

// Component import
import { Button, DefaultComponent } from '@components/index';

// Style import
import { Container, Content, Option, OptionText } from './styles';
import { AlertText } from '@global/styles';

type CompanySegment = 'SUPPLIER' | 'BUYER';

interface Options {
  name: 'Comprar' | 'Fornecer';
  value: CompanySegment;
}

const options: Options[] = [
  { name: 'Comprar', value: 'BUYER' },
  { name: 'Fornecer', value: 'SUPPLIER' },
];

export const Step1: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<SignUpRoutes, 'Step1'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // Hook
  const { user, setUser } = useSignUpForm();

  const defaultValue: CompanySegment | undefined =
    user.isFornecedor === true
      ? 'SUPPLIER'
      : user.isFornecedor === false
      ? 'BUYER'
      : undefined;

  // State
  const [companySegment, setCompanySegment] = useState<
    CompanySegment | undefined
  >(defaultValue);

  const icon: IconProps = {
    color:
      companySegment === undefined ? theme.COLORS.GRAY_300 : theme.COLORS.WHITE,
    weight: companySegment === undefined ? 'regular' : 'bold',
  };

  function handleSelectOption(value: CompanySegment) {
    setCompanySegment(value);

    setUser(prevUserData => ({
      ...prevUserData,
      isFornecedor: value === 'SUPPLIER',
    }));
  }

  return (
    <Container>
      <DefaultComponent
        headerProps={{ goBack: () => navigation.goBack() }}
        highlightProps={{
          title: 'Qual a principal intenção da sua empresa?',
          subtitle: 'Passo 1 de 5',
        }}
        key="default-component-sing-in"
      />

      <Content>
        <AlertText>
          Lembrando que se você é um fornecedor, você também poderá realizar
          compras 😉
        </AlertText>

        {options.map(option => (
          <Option
            key={option.value}
            onPress={() => handleSelectOption(option.value)}
            active={companySegment === option.value}
          >
            <OptionText active={companySegment === option.value}>
              {option.name}
            </OptionText>
          </Option>
        ))}
      </Content>

      <Button
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={icon.color} weight={icon.weight} />}
        bottom
        onPress={() => navigation.navigate('Step2')}
        disabled={companySegment === undefined}
      />
    </Container>
  );
};
