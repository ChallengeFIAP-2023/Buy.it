import { useLayoutEffect, useState } from 'react';
import { ArrowRight } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import Toast from 'react-native-toast-message';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { CreateQuoteRoutes } from '..';
import { TFlatList } from 'react-native-input-select/lib/typescript/types/index.types';

// Theme import
import theme from '@theme/index';

// Component import
import {
  Button,
  CustomDropdown,
  DecreasingContainer,
  DefaultComponent,
  Input,
  WrapperPage,
} from '@components/index';

//Hooks import
import { useCreateQuote } from '@hooks/useCreateQuote';

// Style import
import { ScrollableContent, Fieldset } from '@global/styles/index';
import { CustomModal } from '@components/Modal';

export const Step1: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<CreateQuoteRoutes, 'Step1'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // State
  const [selectedTags, setSelectedTags] = useState<number[]>([]);
  const [selectedDepartment, setSelectedDepartment] = useState<number>(0);
  const [isModalVisible, setModalVisible] = useState(false);
  const [tagName, setTagName] = useState("");
  const [newTagError, setNewTagError] = useState<string | undefined>();

  const toggleModal = () => {
    setModalVisible(!isModalVisible);
  };

  const { 
    setProduct, 
    tags, 
    departments, 
    handleNewTag,
    fetchDepartmentsAndTags 
  } = useCreateQuote();

  useLayoutEffect(() => {
    fetchDepartmentsAndTags();
  }, []);

  const newTag = () => {
    if (!tagName) return setNewTagError("Nome da tag é obrigatório!");
    
    const id = handleNewTag({ nome: tagName });

    if (typeof id === 'number') setSelectedTags(tags => [...tags, id]);

    // TODO: verificar uma forma de mostrar o toast na frente do modal
    return Toast.show({
      type: 'success',
      text1: 'Tag criada com sucesso',
    });
  };

  const onSubmit = () => {
    const idDepartamento = selectedDepartment;
    const idsTags = selectedTags;

    const findError = () => {
      if (!idDepartamento || idDepartamento == 0) return 'Selecione o departamento!' 
      if (!idsTags || idsTags.length <= 0) return 'Selecione pelo menos uma tag!'
      return null;
    };

    const error = findError();

    if (error) 
      return Toast.show({
        type: 'error',
        text1: error,
      });

    setProduct(prevProd => ({ ...prevProd, idDepartamento, idsTags }));

    return navigation.navigate('Step2');
  };

  const departmentsOptions: TFlatList = departments.map(item => ({
    label: item.nome,
    value: item.id,
  }));

  const tagsOptions: TFlatList = tags.map(tag => ({
    label: tag.nome,
    value: tag.id,
  }));

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: 'De que tipo de produto você precisa hoje?',
            subtitle: 'Passo 1 de 5',
          }}
          key="default-component-quote-details"
        />

        <DecreasingContainer>
          <Fieldset>
            <CustomDropdown
              isSearchable
              label="Departamento"
              placeholder="Selecione o departamento relacionado"
              options={departmentsOptions}
              selectedValue={selectedDepartment}
              onValueChange={(value: number) => setSelectedDepartment(value)}
              listControls={{
                emptyListMessage: 'Nenhum departamento encontrado',
              }}
            />
          </Fieldset>

          <Fieldset>
            <CustomDropdown
              label="Tags relacionadas"
              isSearchable
              isMultiple
              placeholder="Selecione as tags relacionadas"
              options={tagsOptions}
              selectedValue={selectedTags}
              onValueChange={(value: []) => setSelectedTags(value)}
              listFooterComponent={
                <Button 
                  label="Nova tag" 
                  size="SM"
                  style={{ margin: 15 }}
                  onPress={toggleModal}
                />
              }
            />
          </Fieldset>
    
        </DecreasingContainer>
      </ScrollableContent>

      <CustomModal
        modalProps={{
          isVisible: isModalVisible,
        }}
        title="Nova tag"
        subtitle="Tags nos ajudam a selecionar os melhores fornecedores para a cotação"
        onClose={toggleModal}
      >
        <Fieldset>
          <Input
            value={tagName}
            onChangeText={(value) => setTagName(value)}
            placeholder="Papelaria"
            error={newTagError}
          />
        </Fieldset>
        
        <Button
          label="Criar"
          size="LG"
          onPress={() => newTag()}
        />
      </CustomModal>

      <Button
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={() => onSubmit()}
      />
    </WrapperPage>
  );
};
