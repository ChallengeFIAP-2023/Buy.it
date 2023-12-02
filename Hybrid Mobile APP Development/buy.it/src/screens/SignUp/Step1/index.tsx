import { useState } from "react";
import { ArrowRight, IconProps } from "phosphor-react-native";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { CompositeScreenProps } from "@react-navigation/native";

// Type import
import { MainNavigationRoutes } from "@routes/index";
import { SignUpRoutes } from "..";

// Theme import
import theme from "@theme/index";

// Component import
import { Button, DefaultComponent } from "@components/index";

// Style import
import { Container, Content, Option, OptionText, AlertText } from './styles';

type CompanySegment = "SUPPLIER" | "BUYER";

export const Step1: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<SignUpRoutes, 'Step1'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // State
  const [companySegment, setCompanySegment] =
    useState<CompanySegment | undefined>(undefined);

  const icon: IconProps = {
    color: companySegment === undefined ?
      theme.COLORS.GRAY_300 :
      theme.COLORS.WHITE,
    weight: companySegment === undefined ? "regular" : "bold"
  }

  return (
    <Container>
      <DefaultComponent
        headerProps={{ goBack: () => navigation.goBack() }}
        highlightProps={{
          title: "Qual a principal intenção da sua empresa?",
          subtitle: "Passo 1 de 5",
        }}
        key="default-component-sing-in"
      />

      <Content>
        <AlertText>Lembrando que se você é um fornecedor, você também poderá realizar compras 😉</AlertText>

        <Option
          onPress={() => setCompanySegment('BUYER')}
          active={companySegment === 'BUYER'}
        >
          <OptionText active={companySegment === 'BUYER'}>Comprar</OptionText>
        </Option>

        <Option
          onPress={() => setCompanySegment('SUPPLIER')}
          active={companySegment === 'SUPPLIER'}
        >
          <OptionText active={companySegment === 'SUPPLIER'}>
            Fornecer
          </OptionText>
        </Option>
      </Content>

      <Button
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={icon.color} weight={icon.weight} />}
        bottom
        onPress={() => navigation.navigate("Step2")}
        disabled={companySegment === undefined}
      />
    </Container>
  );
}
