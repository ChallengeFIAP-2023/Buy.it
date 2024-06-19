import { ActivityIndicator, TouchableOpacity, } from 'react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { Check, DotsThree, X } from 'phosphor-react-native';
import Icon from '@expo/vector-icons/FontAwesome6';
import { Fragment, useLayoutEffect, useState } from 'react';

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
  Chip,
  Button,
  QuoteInfo,
} from '@components/index';

// Style import
import { Flex, ScrollableContent } from '@global/styles/index';
import {
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
  TouchableText
} from './styles';

// Util import
import { toMaskedCurrency } from '@utils/masks';

// Component import
import { CustomModal } from '@components/Modal';
import { UserInfo } from '@components/UserInfo';

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
    fetchContacts,
    proposal, 
    approveLoading, 
    declineLoading 
  } = useQuoteProposal();

  const [isDeclineModalVisible, setDeclineModalVisible] = useState(false);
  const [isAcceptModalVisible, setAcceptModalVisible] = useState(false);
  const [showDetails, setShowDetails] = useState(false);

  const toggleDeclineModal = () => setDeclineModalVisible(modal => !modal);
  const toggleAcceptModal = () => setAcceptModalVisible(modal => !modal);

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
    if(proposal) fetchContacts();
  }, [proposal]);

  const buttonIsDisabled = approveLoading || declineLoading;
  const hasDetails = proposal && (proposal.produto) && 
  (proposal.produto.marca || proposal.produto.cor || 
    proposal.produto.material || proposal.produto.tamanho);
  
  return (
    <WrapperPage>
      <ScrollableContent>
        <UserInfo user={proposal?.comprador}/>

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

          {showDetails && proposal && (
            <QuoteInfo quote={proposal} />
          )}

          {hasDetails && (
            <Button 
              style={{
                marginBottom: 200,
              }}
              label={`${showDetails ? "menos" : "mais"} detalhes`}
              type="secondary"
              size="LG"
              onPress={() => setShowDetails(!showDetails)}
              iconFirst={showDetails}
              icon={
                <Icon 
                  name={showDetails ? "chevron-up" : "chevron-down"}
                  size={theme.FONT_SIZE.LG}
                  color={theme.COLORS.PRIMARY_LIGHTER}
                />
              }
            />
          )}
        </DecreasingContainer>
      </ScrollableContent>

      <ActionsButton>
        <Refuse
          onPress={() => toggleDeclineModal()}
          disabled={buttonIsDisabled}
        >
          <X size={40} color={theme.COLORS.GRAY_600} weight="bold" />
        </Refuse>

        <Accept
          onPress={() => toggleAcceptModal()}
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
        modalProps={{ isVisible: isDeclineModalVisible }}
        title="Nos ajude a melhorar"
        subtitle="Respondendo nossas perguntas você aumenta as chances de conseguir novas vendas!"
        onClose={toggleDeclineModal}
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

      <CustomModal
        modalProps={{ isVisible: isAcceptModalVisible }}
        title="Revise os dados"
        subtitle="É importante revisar as informações antes de aceitar"
        onClose={toggleAcceptModal}
      >
        {proposal && 
          <QuoteInfo 
            quote={proposal} 
            contained 
            showMainInformation 
          /> 
        }
        <Flex style={{ marginTop: 20 }}>
          <Button 
            size='MD' 
            label='Cancelar' 
            style={{ flex: 1 }} 
            backgroundColor={theme.COLORS.GRAY_400}
            onPress={() => toggleAcceptModal()}
          />
          <Button 
            size='MD' 
            label='Aceitar' 
            style={{ flex: 1 }}             
            backgroundColor={theme.COLORS.GREEN_800}
            onPress={() => handleProcessProposal("approve")}
          />
        </Flex>
      </CustomModal>
    </WrapperPage>
  );
};