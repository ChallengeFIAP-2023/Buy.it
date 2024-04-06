import { ActivityIndicator, ImageSourcePropType } from 'react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { Check, DotsThree, Truck, X } from 'phosphor-react-native';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { QuoteProposalRoutes } from '..';

//Hooks import
import { useAuth } from '@hooks/useAuth';
import { useQuoteProposal } from '@hooks/useQuoteProposal';

// Theme import
import theme from '@theme/index';

// Component import
import {
  DecreasingContainer,
  WrapperPage,
  UserAvatar as CompanyImage,
  Chip,
} from '@components/index';

// Style import
import { Flex, ScrollableContent } from '@global/styles/index';
import {
  CompanyWrapper,
  CompanyData,
  CompanyName,
  CompanyDocument,
  ProductDetails,
  DescriptionContainer,
  Description,
  ProductQuantity,
  ProductName,
  PerText,
  TagWrapper,
  ProductPriceContainer,
  Price,
  DeliveryContainer,
  DeliveryText,
  ActionsButton,
  Refuse,
  Accept,
} from './styles';
import { toMaskedCNPJ, toMaskedCurrency } from '@utils/masks';
import { Fragment } from 'react';

const maximumTags = 8;

export const QuoteProposalDetails: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuoteProposalRoutes, 'QuoteProposalDetails'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // Hook
  const { handleProcessProposal, proposal, approveLoading, declineLoading } =
    useQuoteProposal();

  const imageSource: ImageSourcePropType = proposal?.comprador.urlImagem
    ? { uri: proposal?.comprador.urlImagem }
    : require('../../../assets/default_avatar.png');

  const tags = Array.isArray(proposal?.produto.tags)
    ? proposal?.produto.tags
    : [];

  function deadlineLabel() {
    if (proposal?.prazo && proposal?.prazo > 1)
      return `${proposal?.prazo} dias`;

    return `${proposal?.prazo} dia`;
  }

  const buttonIsDisabled = approveLoading || declineLoading;

  return (
    <WrapperPage>
      <ScrollableContent>
        <CompanyWrapper>
          <CompanyImage imageSource={imageSource} size="MD" />

          <CompanyData>
            <CompanyName numberOfLines={1}>
              {proposal?.comprador.nome ?? 'Empresa não identificada'}
            </CompanyName>
            <CompanyDocument>
              {toMaskedCNPJ(proposal?.comprador.cnpj ?? '')}
            </CompanyDocument>
          </CompanyData>
        </CompanyWrapper>

        <DescriptionContainer>
          <Description>
            Criou uma cotação que pode ser do seu interesse
          </Description>

          {tags[0] && (
            <Fragment>
              <TagWrapper>
                {tags.map(item => (
                  <Chip
                    value={item.nome}
                    key={`${item.nome}-${Math.random()}`}
                  />
                ))}
              </TagWrapper>

              {tags && tags.length >= maximumTags && (
                <Flex style={{ justifyContent: 'flex-start' }}>
                  <DotsThree size={40} color={theme.COLORS.WHITE} />
                  <Description>
                    Mais {tags.length - maximumTags} tags
                  </Description>
                </Flex>
              )}
            </Fragment>
          )}
        </DescriptionContainer>

        <DecreasingContainer>
          <ProductDetails>
            <ProductQuantity>
              {proposal?.quantidadeProduto} unidades de
            </ProductQuantity>
            <ProductName>Caneta Esferográfica</ProductName>
            <PerText>por</PerText>

            <ProductPriceContainer>
              <PerText>R$</PerText>
              <Price>
                {toMaskedCurrency(Number(proposal?.valorProduto), false)}
              </Price>
              <PerText>cada</PerText>
            </ProductPriceContainer>

            <DeliveryContainer>
              <Truck size={32} color={theme.COLORS.WHITE} />
              <DeliveryText>
                Para entrega em{' '}
                <DeliveryText style={{ fontWeight: '800' }}>
                  {deadlineLabel()}
                </DeliveryText>
              </DeliveryText>
            </DeliveryContainer>
          </ProductDetails>
        </DecreasingContainer>
      </ScrollableContent>

      <ActionsButton>
        <Refuse
          onPress={() => handleProcessProposal('decline')}
          disabled={buttonIsDisabled}
        >
          {declineLoading ? (
            <ActivityIndicator color={theme.COLORS.WHITE} size={40} />
          ) : (
            <X size={40} color={theme.COLORS.GRAY_600} weight="bold" />
          )}
        </Refuse>

        <Accept
          onPress={() => handleProcessProposal('approve')}
          disabled={buttonIsDisabled}
        >
          {approveLoading ? (
            <ActivityIndicator color={theme.COLORS.WHITE} size={40} />
          ) : (
            <Check size={40} color={theme.COLORS.GRAY_600} weight="bold" />
          )}
        </Accept>
      </ActionsButton>
    </WrapperPage>
  );
};
