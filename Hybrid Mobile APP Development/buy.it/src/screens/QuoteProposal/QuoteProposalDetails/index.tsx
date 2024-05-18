import { ActivityIndicator, ImageSourcePropType, Touchable, TouchableOpacity } from 'react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { Check, DotsThree, X } from 'phosphor-react-native';
import Icon from '@expo/vector-icons/FontAwesome6';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { QuoteProposalRoutes } from '..';

//Hooks import
import { useQuoteProposal } from '@hooks/useQuoteProposal';

// Theme import
import theme from '@theme/index';

// Component import
import {
  DecreasingContainer,
  WrapperPage,
  UserAvatar as CompanyImage,
  Chip,
  Button,
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
  SmallText,
  TouchableText,
} from './styles';
import { toMaskedCNPJ, toMaskedCurrency } from '@utils/masks';
import { Fragment, useLayoutEffect, useState } from 'react';
import { CustomModal } from '@components/Modal';

const maximumTags = 8;

export const QuoteProposalDetails: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuoteProposalRoutes, 'QuoteProposalDetails'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // Hook
  const { 
    handleProcessProposal,
    fetchProposals, 
    proposal, 
    approveLoading, 
    declineLoading 
  } = useQuoteProposal();

  const [isModalVisible, setModalVisible] = useState(false);
  const toggleModal = () => setModalVisible(modal => !modal);

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

  useLayoutEffect(() => {
    if(!proposal) fetchProposals();
  }, [proposal]);

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
            <ProductName>{proposal?.produto.nome}</ProductName>
            <PerText>por</PerText>

            <ProductPriceContainer>
              <PerText>R$</PerText>
              <Price>
                {toMaskedCurrency(String(proposal?.valorProduto.toFixed(2)), false)}
              </Price>
              <PerText>cada</PerText>
            </ProductPriceContainer>

            <DeliveryContainer>
              <Icon 
                name={"truck"} 
                size={theme.FONT_SIZE.XL} color={theme.COLORS.WHITE} 
              />
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
          onPress={() => toggleModal()}
          disabled={buttonIsDisabled}
        >
          <X size={40} color={theme.COLORS.GRAY_600} weight="bold" />
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

      <CustomModal
        modalProps={{ isVisible: isModalVisible }}
        title="Nos ajude a melhorar"
        subtitle="Respondendo nossas perguntas você aumenta as chances de conseguir novas vendas!"
        onClose={toggleModal}
      >
        <SmallText>Fornecedor, que tal nos ajudar respondendo algumas perguntas sobre os motivos de ter recusado esta proposta?</SmallText>
        <SmallText>Desta forma, conseguimos te enviar somente propostas alinhadas com a sua empresa, aumentando suas chances de venda!</SmallText>

        <Button
          size='MD'
          label='Responder perguntas'
          onPress={() => navigation.navigate("QuoteProposalRefused", { id: Number(proposal?.id) })} 
          style={{
            marginTop: 15
          }}
        />
        <TouchableOpacity onPress={() => handleProcessProposal('decline')}>
          <TouchableText>Pular por enquanto</TouchableText>
        </TouchableOpacity>
      </CustomModal>
    </WrapperPage>
  );
};
